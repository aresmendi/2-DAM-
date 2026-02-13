import 'package:flutter/material.dart';

void main() async {
  runApp(MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(home: MainWidget());
  }
}

class MainWidget extends StatelessWidget {
  const MainWidget({super.key});
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('State demo')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            CounterWidget(
              label: 'Counter 1',
              initialValue: 5,
              step: 5,
              min: 0,
              max: 50,
            ),
            SizedBox(height: 20),
            CounterWidget(
              label: 'Counter 2',
              initialValue: -5,
              step: 2,
              min: -20,
              max: 100,
            ),
          ],
        ),
      ),
    );
  }
}

class CounterWidget extends StatefulWidget {
  final String? label;
  final int? initialValue;
  final int? step;
  final int? min;
  final int? max;
  CounterWidget({
    Key? key,
    this.label,
    this.initialValue,
    this.step,
    this.min,
    this.max,
  }) : super(key: key);
  @override
  State<StatefulWidget> createState() => _CounterWidgetState();
}

class _CounterWidgetState extends State<CounterWidget> {
  //guardamos el estado
  int _counter = 0;
  //iniciamos el estado
  @override
  initState() {
    super.initState();
    setState(() {
      _counter = widget.initialValue ?? 0;
    });
  }

  //getters y setters para acceder a las propiedades del widget
  String get label => widget.label ?? '';
  int get step => widget.step ?? 1;
  int get min => widget.min ?? 0;
  int get max => widget.max ?? 100;
  int get initialValue => widget.initialValue ?? 0;
  //métodos para incrementar y decrementar el contador
  void _incrementCounter() {
    setState(() {
      _counter += step;
      if (max != null && _counter > max) _counter = max;
    });
  }

  void _decrementCounter() {
    setState(() {
      _counter -= step;
      if (min != null && _counter < min) _counter = min;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          if (label.isNotEmpty) Text(label!, style: TextStyle(fontSize: 18)),
          SizedBox(width: 20),
          IconButton.outlined(
            onPressed: _decrementCounter,
            icon: Icon(Icons.remove),
          ),
          SizedBox(width: 10),
          Container(
            width: 30,
            alignment: Alignment.center,
            child: Text(
              '$_counter',
              style: TextStyle(
                fontSize: 20,
                fontWeight: FontWeight.bold,
                //Cambiar color de texto en función del valor del contador
                color: _counter < 0 ? Colors.red : Colors.green,
              ),
            ),
          ),
          SizedBox(width: 10),
          IconButton.outlined(
            onPressed: _incrementCounter,
            icon: Icon(Icons.add),
          ),
        ],
      ),
    );
  }
}
