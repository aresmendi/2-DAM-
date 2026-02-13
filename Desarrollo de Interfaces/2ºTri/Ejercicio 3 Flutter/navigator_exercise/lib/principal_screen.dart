import 'package:flutter/material.dart';
import 'add_registros_screen.dart';

class PrincipalScreen extends StatefulWidget {
  const PrincipalScreen({super.key});
  @override
  State<PrincipalScreen> createState() => _PrincipalScreenState();
}

class RegistroIMC {
  final double altura;
  final double peso;

  RegistroIMC(this.altura, this.peso);

  double get imc => peso / (altura * altura);
}

class _PrincipalScreenState extends State<PrincipalScreen> {
  final List<RegistroIMC> registros = [];
  double get imcActual => registros.isEmpty ? 0 : registros.last.imc;

  void _recibirRegistro(RegistroIMC registro) {
    setState(() {
      registros.add(registro);
    });
  }

  void _addRegistro() {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (_) => AddRegistroScreen(
          onGuardar: _recibirRegistro,
        ),
      ),
    );
  }

  String get interpretacionIMC {
    final imc = imcActual;
    if (imc == 0) return 'Sin datos';
    if (imc < 18.5) return 'Delgadez';
    if (imc < 25) return 'Saludable';
    if (imc < 30) return 'Sobrepeso';
    return 'Obesidad';
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text("Pantalla Principal"),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _addRegistro,
        child: const Icon(Icons.add),
      ),
      body: Column(
        children: [
          _buildIMCHeader(),
          Expanded(child: _buildListaRegistros()),
        ],
      ),
    );
  }

  //Mostramos los IMCs
  Widget _buildIMCHeader() {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.all(20),
      color: Theme.of(context).colorScheme.primary,
      child: Column(
        children: [
          const Text(
            'IMC Actual',
            style: TextStyle(color: Colors.white, fontSize: 20),
          ),
          const SizedBox(height: 10),
          Text(
            imcActual.toStringAsFixed(2),
            style: const TextStyle(
              color: Colors.white,
              fontSize: 40,
              fontWeight: FontWeight.bold,
            ),
          ),
          Text(
            interpretacionIMC,
            style: const TextStyle(color: Colors.white, fontSize: 18),
          ),
        ],
      ),
    );
  }

  //Mostramos la listas de registros
  Widget _buildListaRegistros() {
    if (registros.isEmpty) {
      return const Center(child: Text('No hay registros'));
    }
    return ListView.builder(
      itemCount: registros.length,
      itemBuilder: (context, index) {
        final r = registros[index];
        final imc = r.peso / (r.altura * r.altura);

        return ListTile(
          leading: const Icon(Icons.monitor_weight),
          title: Text('Peso: ${r.peso} kg - Altura: ${r.altura} m'),
          subtitle: Text('IMC: ${imc.toStringAsFixed(2)}'),
        );
      },
    );
  }
}
