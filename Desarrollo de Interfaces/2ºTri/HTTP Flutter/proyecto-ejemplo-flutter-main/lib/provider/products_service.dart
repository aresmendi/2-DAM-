import 'dart:async';
import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import '../models/product.dart';

class ProductsService extends ChangeNotifier {
  static const int timeout = 3;

  static const String host = 'localhost';
  static const int port = 3000;
  static const String path = '/products';

  List<Product> products = [];
  List<Product> filteredProducts = [];
  String lastError = '';
  bool _loading = false;

  String searchFilter = '';
  bool? sortNameAsc;
  bool? sortPriceAsc;
  bool? sortRatingAsc;

  bool get loading => _loading;

  ProductsService() {
    updateProducts();
  }

  updateProducts() {
    getProducts().then((value) {
      products = value;
      filteredProducts = value;
      applyFilters();
    });
  }

  /// GET -> http://localhost:3000/products
  Future<List<Product>> getProducts() async {
    setError('');
    _loading = true;
    notifyListeners();

    try {
      final uri = Uri.parse('http://$host:$port$path');

      final response =
          await http.get(uri).timeout(const Duration(seconds: timeout));

      if (response.statusCode >= 200 && response.statusCode < 300) {
        List jsonList = jsonDecode(response.body);
        List<Product> products = [];

        for (var item in jsonList) {
          Product product = Product.fromJson(item);
          products.add(product);
        }

        this.products = products;
        _loading = false;
        notifyListeners();
        return products;
      } else {
        setError(
            'Error al obtener listado de productos. ${response.statusCode}');
        _loading = false;
        return [];
      }
    } catch (e) {
      setError('Error al obtener listado de productos. $e');
      _loading = false;
      return products;
    }
  }

  /// POST -> http://localhost:3000/products
  Future<Product?> addProduct(Product product) async {
    _loading = true;
    setError('');
    notifyListeners();

    try {
      final uri = Uri.parse('http://$host:$port$path');

      final response = await http
          .post(
            uri,
            headers: {'Content-Type': 'application/json'},
            body: jsonEncode(product),
          )
          .timeout(const Duration(seconds: timeout));

      if (response.statusCode >= 200 && response.statusCode < 300) {
        _loading = false;
        return Product.fromJson(jsonDecode(response.body));
      } else {
        setError('Error al crear producto. ${response.statusCode}');
        _loading = false;
        return null;
      }
    } catch (e) {
      setError('Error al crear producto. $e');
      _loading = false;
      return null;
    }
  }

  /// DELETE -> http://localhost:3000/products/{id}
  Future<Product?> removeProduct(String id) async {
    _loading = true;
    setError('');

    try {
      final uri = Uri.parse('http://$host:$port$path/$id');

      final response =
          await http.delete(uri).timeout(const Duration(seconds: timeout));

      if (response.statusCode >= 200 && response.statusCode < 300) {
        _loading = false;
        return Product.fromJson(jsonDecode(response.body));
      } else {
        setError('Error al borrar producto. ${response.statusCode}');
        _loading = false;
        return null;
      }
    } catch (e) {
      setError('Error al borrar producto. $e');
      _loading = false;
      return null;
    }
  }

  /// GET -> http://localhost:3000/products/{id}
  Future<Product?> getProduct(String id) async {
    _loading = true;
    setError('');
    notifyListeners();

    try {
      final uri = Uri.parse('http://$host:$port$path/$id');

      final response =
          await http.get(uri).timeout(const Duration(seconds: timeout));

      if (response.statusCode >= 200 && response.statusCode < 300) {
        _loading = false;
        return Product.fromJson(jsonDecode(response.body));
      } else {
        setError('Error al obtener producto. ${response.statusCode}');
        _loading = false;
        return null;
      }
    } catch (e) {
      setError('Error al obtener producto. $e');
      _loading = false;
      return null;
    }
  }

  /// PUT -> http://localhost:3000/products/{id}
  Future<Product?> modifyProduct(Product product) async {
    _loading = true;
    setError('');
    notifyListeners();

    try {
      final uri = Uri.parse('http://$host:$port$path/${product.id}');

      final response = await http
          .put(
            uri,
            headers: {'Content-Type': 'application/json'},
            body: jsonEncode(product),
          )
          .timeout(const Duration(seconds: timeout));

      if (response.statusCode >= 200 && response.statusCode < 300) {
        _loading = false;
        return Product.fromJson(jsonDecode(response.body));
      } else {
        setError('Error al modificar producto. ${response.statusCode}');
        _loading = false;
        return null;
      }
    } catch (e) {
      setError('Error al modificar producto. $e');
      _loading = false;
      return null;
    }
  }

  void setError(String error) {
    lastError = error;
  }

  filterProducts(String value) {
    searchFilter = value;
    applyFilters();
  }

  sortName() {
    sortNameAsc = sortNameAsc == null ? true : !sortNameAsc!;
    resetSortPrice();
    resetSortRating();
    applyFilters();
  }

  sortPrice() {
    sortPriceAsc = sortPriceAsc == null ? true : !sortPriceAsc!;
    resetSortName();
    resetSortRating();
    applyFilters();
  }

  sortRating() {
    sortRatingAsc = sortRatingAsc == null ? true : !sortRatingAsc!;
    resetSortName();
    resetSortPrice();
    applyFilters();
  }

  resetSortName() => sortNameAsc = null;
  resetSortPrice() => sortPriceAsc = null;
  resetSortRating() => sortRatingAsc = null;

  applyFilters() {
    filteredProducts = products
        .where((element) => element.description
            .toLowerCase()
            .contains(searchFilter.toLowerCase()))
        .toList();

    if (sortNameAsc != null) {
      sortNameAsc!
          ? filteredProducts
              .sort((a, b) => a.description.compareTo(b.description))
          : filteredProducts
              .sort((a, b) => b.description.compareTo(a.description));
    }

    if (sortPriceAsc != null) {
      sortPriceAsc!
          ? filteredProducts.sort((a, b) => a.price.compareTo(b.price))
          : filteredProducts.sort((a, b) => b.price.compareTo(a.price));
    }

    if (sortRatingAsc != null) {
      sortRatingAsc!
          ? filteredProducts.sort((a, b) => a.rating.compareTo(b.rating))
          : filteredProducts.sort((a, b) => b.rating.compareTo(a.rating));
    }

    notifyListeners();
  }
}
