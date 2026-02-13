import 'dart:io';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'models/producto.dart';
import 'provider/products_service.dart';

void main() {
  runApp(
    ChangeNotifierProvider(
      create: (_) => ProductsService(),
      child: const ProductsApp(),
    ),
  );
}

class ProductsApp extends StatelessWidget {
  const ProductsApp({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Products',
      debugShowCheckedModeBanner: false,
      home: const Products(),
    );
  }
}

class Products extends StatelessWidget {
  const Products({super.key});
  @override
  Widget build(BuildContext context) {
    final service = context.watch<ProductsService>();
    return Scaffold(
      appBar: AppBar(
        title: const Text('Productos'),
        actions: [
          IconButton(
            icon: const Icon(Icons.add),
            onPressed: () {
              // Producto creado por defecto
              final newProduct = Product(
                // ❌ NO se envía el id
                description: 'Producto de prueba',
                price: 9.99,
                available: DateTime.now(),
                rating: 4,
                imageUrl: '',
              );
              context.read<ProductsService>().createProduct(newProduct);
            },
          ),
        ],
      ),
      body: ListView.builder(
        itemCount: service.products.length,
        itemBuilder: (context, index) {
          final Product product = service.products[index];
          return ListTile(
            leading: product.imageUrl.isNotEmpty
                ? (Uri.parse(product.imageUrl).isAbsolute
                      ? Image.network(
                          product.imageUrl,
                          fit: BoxFit.cover,
                          width: 50,
                          height: 50,
                        )
                      : Image.file(
                          File(product.imageUrl),
                          fit: BoxFit.cover,
                          width: 50,
                          height: 50,
                        ))
                : const Icon(Icons.image_outlined, size: 50),
            title: Text(product.description),
            subtitle: Text('${product.price} €'),
            trailing: Row(
              mainAxisSize: MainAxisSize.min,
              children: [
                for (var i = 0; i < product.rating; i++)
                  const Icon(Icons.star, color: Colors.amber, size: 14),
                for (var i = 0; i < 5 - product.rating; i++)
                  const Icon(Icons.star, color: Colors.grey, size: 14),
                IconButton(
                  icon: const Icon(Icons.delete, color: Colors.red),
                  onPressed: () {
                    context.read<ProductsService>().deleteProduct(product.id!);
                  },
                ),
                // BOTÓN MODIFICAR (PUT)
                IconButton(
                  icon: const Icon(Icons.edit, color: Colors.blue),
                  onPressed: () {
                    if (product.id == null) return;
                    final updatedProduct = Product(
                      id: product.id, // MISMO id
                      description: '${product.description} (modificado)',
                      price: product.price + 1,
                      available: product.available,
                      imageUrl: product.imageUrl,
                      rating: product.rating,
                    );
                    context.read<ProductsService>().updateProduct(
                      updatedProduct,
                    );
                  },
                ),
              ],
            ),
          );
        },
      ),
    );
  }
}
