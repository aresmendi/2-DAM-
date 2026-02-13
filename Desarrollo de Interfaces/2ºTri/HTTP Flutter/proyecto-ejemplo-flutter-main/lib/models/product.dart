class Product {
  static const String idKey = 'id';
  static const String descriptionKey = 'description';
  static const String priceKey = 'price';
  static const String availableKey = 'available';
  static const String imageUrlKey = 'imageUrl';
  static const String ratingKey = 'rating';

  final String? id;
  final String description;
  final double price;
  final DateTime available;
  final String imageUrl;
  final int rating;

  const Product({
    this.id,
    required this.description,
    required this.price,
    required this.available,
    required this.imageUrl,
    required this.rating,
  });

  Product.fromJson(Map<String, dynamic> json)
      : id = json[idKey],
        description = json[descriptionKey] != null
            ? json[descriptionKey].toString()
            : 'Sin descripción',
        price = double.tryParse(json[priceKey].toString()) ?? 0.0,
        available = json[availableKey] != null
            ? DateTime.parse(json[availableKey].toString())
            : DateTime.now(),
        imageUrl =
            json[imageUrlKey] != null ? json[imageUrlKey].toString() : '',
        rating = int.tryParse(json[ratingKey].toString()) ?? 0;

  Map<String, dynamic> toJson() {
    if (id == null) {
      return {
        descriptionKey: description,
        priceKey: price,
        availableKey: available.toIso8601String(),
        imageUrlKey: imageUrl,
        ratingKey: rating
      };
    } else {
      return {
        idKey: id,
        descriptionKey: description,
        priceKey: price,
        availableKey: available.toIso8601String(),
        imageUrlKey: imageUrl,
        ratingKey: rating
      };
    }
  }
}
