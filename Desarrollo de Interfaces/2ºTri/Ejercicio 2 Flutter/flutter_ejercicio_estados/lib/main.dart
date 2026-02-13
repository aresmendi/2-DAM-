import 'package:flutter/material.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: MainWidget(),
    );
  }
}

class MainWidget extends StatefulWidget {
  const MainWidget({super.key});

  @override
  State<MainWidget> createState() => _MainWidgetState();
}

class _MainWidgetState extends State<MainWidget> {
  String _color = '';

  Color _getColor() {
    switch (_color) {
      case 'red':
        return Colors.red;
      case 'green':
        return Colors.green;
      case 'blue':
        return Colors.blue;
      default:
        return Colors.grey;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(
              Icons.lightbulb_outline,
              size: 50,
              color: _getColor(),
            ),
            const SizedBox(height: 50),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                MaterialButton(
                  onPressed: () {
                    setState(() {
                      _color = 'red';
                    });
                  },
                  shape: BoxBorder.all(color: Colors.black, width: 2),
                  color: _color == 'red'
                      ? Colors.red
                      : Colors.transparent,
                  child: const Text('Rojo'),
                ),
                const SizedBox(width: 20),
                MaterialButton(
                  onPressed: () {
                    setState(() {
                      _color = 'green';
                    });
                  },
                  shape: BoxBorder.all(color: Colors.black, width: 2),
                  color: _color == 'green'
                      ? Colors.green
                      : Colors.transparent,
                  child: const Text('Verde'),
                ),
                const SizedBox(width: 20),
                MaterialButton(
                  onPressed: () {
                    setState(() {
                      _color = 'blue';
                    });
                  },
                  shape: BoxBorder.all(color: Colors.black, width: 2),
                  color: _color == 'blue'
                      ? Colors.blue
                      : Colors.transparent,
                  child: const Text('Azul'),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
