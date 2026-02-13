import 'dart:async';
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import '../models/producto.dart';

class ProductsService extends ChangeNotifier {
  static const int timeout = 3;
  static const String host = 'localhost';
  static const int port = 3000;
  static const String path = '/products';
  List<Product> products = [];
  ProductsService() {
    loadProducts();
  }
  Future<void> loadProducts() async {
    try {
      // Construir la URL
      final uri = Uri.parse('http://$host:$port$path');
      //Importante
      //Debemos recordar ejecutar siempre el servicio desde la carpeta server/ .
      // Petición GET
      final response = await http
          .get(uri)
          .timeout(const Duration(seconds: timeout));
      // Comprobar el estado de la respuesta
      if (response.statusCode >= 200 && response.statusCode < 300) {
        // Decodificar el JSON
        final List jsonList = jsonDecode(response.body);
        products = [];
        for (var item in jsonList) {
          Product product = Product.fromJson(item);
          products.add(product);
        }
        notifyListeners();
      } else {
        debugPrint('Error: ${response.statusCode}');
      }
    } catch (e) {
      debugPrint('Error: $e');
    }
  }

  Future<Product?> deleteProduct(String id) async {
    try {
      final uri = Uri.parse('http://$host:$port$path/$id');
      final response = await http
          .delete(uri)
          .timeout(const Duration(seconds: timeout));
      if (response.statusCode >= 200 && response.statusCode < 300) {
        products.removeWhere((p) => p.id == id);
        notifyListeners();
        return null;
      }
    } catch (e) {
      debugPrint('Error DELETE: $e');
    }
    return null;
  }

  Future<Product?> createProduct(Product product) async {
    try {
      // Construir la URL
      final uri = Uri.parse('http://$host:$port$path');
      // Petición POST
      final response = await http
          .post(
            uri,
            headers: {'Content-Type': 'application/json'},
            body: jsonEncode(product.toJson()),
          )
          .timeout(const Duration(seconds: timeout));
      // Comprobar el estado de la respuesta
      if (response.statusCode >= 200 && response.statusCode < 300) {
        // Decodificar la respuesta (producto creado con id asignado)
        final json = jsonDecode(response.body);
        final createdProduct = Product.fromJson(json);
        products.add(createdProduct);
        notifyListeners();
      } else {
        debugPrint('Error POST: ${response.statusCode}');
      }
    } catch (e) {
      debugPrint('Error POST: $e');
    }
    return null;
  }

  Future<void> updateProduct(Product product) async {
    try {
      // Seguridad: solo actualizar si hay id
      if (product.id == null) return;
      // Construir la URL
      final uri = Uri.parse('http://$host:$port$path/${product.id}');
      // Petición PUT
      final response = await http
          .put(
            uri,
            headers: {'Content-Type': 'application/json'},
            body: jsonEncode(product.toJson()),
          )
          .timeout(const Duration(seconds: timeout));
      // Comprobar el estado de la respuesta
      if (response.statusCode >= 200 && response.statusCode < 300) {
        // Decodificar el producto actualizado
        final json = jsonDecode(response.body);
        final updatedProduct = Product.fromJson(json);
        // Actualizar la lista local
        final index = products.indexWhere((p) => p.id == updatedProduct.id);
        if (index != -1) {
          products[index] = updatedProduct;
          notifyListeners();
        }
      } else {
        debugPrint('Error PUT: ${response.statusCode}');
      }
    } catch (e) {
      debugPrint('Error PUT: $e');
    }
  }
}
