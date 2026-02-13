import 'package:flutter/material.dart';
import 'principal_screen.dart';

class AddRegistroScreen extends StatefulWidget {
  final void Function(RegistroIMC) onGuardar;

  const AddRegistroScreen({super.key, required this.onGuardar});

  @override
  State<AddRegistroScreen> createState() => _AddRegistroScreenState();
}

class _AddRegistroScreenState extends State<AddRegistroScreen> {
  final _formKey = GlobalKey<FormState>();
  final _alturaController = TextEditingController();
  final _pesoController = TextEditingController();

  void _guardar() {
  if (!_formKey.currentState!.validate()) return;

  final altura = double.parse(_alturaController.text);
  final peso = double.parse(_pesoController.text);

  widget.onGuardar(RegistroIMC(altura, peso));
  Navigator.pop(context);
}


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Nuevo registro')),
      body: Padding(
        padding: const EdgeInsets.all(20),
        child: Form(
          key: _formKey,
          child: Column(
            children: [
              TextFormField(
                controller: _alturaController,
                keyboardType: TextInputType.number,
                decoration: const InputDecoration(labelText: 'Altura (m)'),
                validator: _validateNumber,
              ),
              TextFormField(
                controller: _pesoController,
                keyboardType: TextInputType.number,
                decoration: const InputDecoration(labelText: 'Peso (kg)'),
                validator: _validateNumber,
              ),
              const SizedBox(height: 20),
              ElevatedButton(onPressed: _guardar, child: const Text('Guardar')),
            ],
          ),
        ),
      ),
    );
  }

  String? _validateNumber(String? value) {
    if (value == null || value.isEmpty) {
      return 'Campo obligatorio';
    }
    final number = double.tryParse(value);
    if (number == null || number <= 0) {
      return 'Introduce un número válido';
    }
    return null;
  }
}
