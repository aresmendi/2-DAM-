//Se mostrará una pantalla de bienvenida, con un icono y un mensaje.
//Puede cerrarse automáticamente, a los pocos segundos o con un botón.
import 'package:flutter/material.dart';
import 'dart:async'; // para poder usar la clase Timer
import 'principal_screen.dart';
class WelcomeScreen extends StatefulWidget {
  const WelcomeScreen({super.key});

  @override
  State<WelcomeScreen> createState() => _WelcomeScreenState();
}

class _WelcomeScreenState extends State<WelcomeScreen> {
  late Timer _timer;

  void nextPage() {
    Navigator.of(context).pushReplacement(
      MaterialPageRoute(builder: (_) => const PrincipalScreen()),
    );
  }

  @override
  void initState() {
    super.initState();

    _timer = Timer(const Duration(seconds: 3), () {
      nextPage();
    });
  }

  @override
  void dispose() {
    _timer.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text("Welcome!"),
      ),
      body: Center(child: _buildWelcomePage()),
    );
  }

  Widget _buildWelcomePage() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        const Text("Welcome to the app!"),
        Icon(Icons.thumb_up, size: 50, color: Colors.redAccent),
        SizedBox(height: 20),
        ElevatedButton(
          onPressed: () {
            _timer.cancel();
            nextPage();
          },
          child: Text("Continuar"),
        ),
      ],
    );
  }
}
