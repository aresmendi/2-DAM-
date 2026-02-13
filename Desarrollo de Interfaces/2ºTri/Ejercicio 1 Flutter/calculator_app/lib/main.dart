import 'package:flutter/material.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('Calculadora')),
        body: Row(
          children: [
            Expanded(
              child: Column(
                children: [
                  Expanded(
                    flex: 1,
                    child: Card(
                      color: Colors.grey,
                      child: Center(
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Align(
                              alignment: Alignment.topLeft,
                              child: Padding(
                                padding: EdgeInsets.only(left: 10, top: 8),
                                child: Text(
                                  "Histórico",
                                  style: TextStyle(
                                    color: const Color.fromARGB(255, 0, 0, 0),
                                    fontSize: 20,
                                  ),
                                ),
                              ),
                            ),
                            Align(
                              alignment: Alignment.topRight,
                              child: Padding(
                                padding: const EdgeInsets.all(8.0),
                                child: Icon(
                                  Icons.more_vert,
                                  color: const Color.fromARGB(255, 0, 0, 0),
                                  size: 20,
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                  ),
                  Expanded(
                    flex: 2,
                    child: Card(
                      color: Colors.grey,
                      child: Center(
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Align(
                              alignment: Alignment.topLeft,
                              child: Padding(
                                padding: EdgeInsets.all(20),
                                child: Text(
                                  "Hoy",
                                  style: TextStyle(
                                    color: const Color.fromARGB(255, 0, 0, 0),
                                    fontSize: 20,
                                  ),
                                ),
                              ),
                            ),
                            Align(
                              alignment: Alignment.bottomRight,
                              child: Padding(
                                padding: const EdgeInsets.all(8.0),
                                child: Text(
                                  "27 + 10\n37\n\n28 * 40\n1.120\n\n400 / 20\n20\n\n20 - 2\n18",
                                  style: TextStyle(
                                    color: const Color.fromARGB(255, 0, 0, 0),
                                    fontSize: 20,
                                  ),
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                  ),
                ],
              ),
            ),
            Expanded(
              flex: 2,
              child: Column(
                children: [
                  Expanded(
                    child: Card(
                      color: Colors.grey,
                      child: Center(
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Align(
                              alignment: Alignment.topRight,
                              child: Padding(
                                padding: const EdgeInsets.all(8.0),
                                child: Icon(
                                  Icons.more_vert,
                                  color: const Color.fromARGB(255, 0, 0, 0),
                                  size: 20,
                                ),
                              ),
                            ),
                            Align(
                              alignment: Alignment.centerRight,
                              child: Padding(
                                padding: const EdgeInsets.all(8.0),
                                child: Text(
                                  "16+(0.5)",
                                  style: TextStyle(
                                    color: const Color.fromARGB(255, 0, 0, 0),
                                    fontSize: 40,
                                  ),
                                ),
                              ),
                            ),
                            Align(
                              alignment: Alignment.bottomRight,
                              child: Padding(
                                padding: const EdgeInsets.all(8.0),
                                child: Text(
                                  "8",
                                  style: TextStyle(
                                    color: const Color.fromARGB(255, 0, 0, 0),
                                    fontSize: 35
                                  ),
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ),
                  ),
                  Expanded(
                    child: Card(
                      color: Colors.white,
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Expanded(
                            child: GridView.count(
                              childAspectRatio: 2.4,
                              crossAxisCount: 1,
                              children: [
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.grey,
                                  child: Center(
                                    child: Text(
                                      "√",
                                      style: TextStyle(
                                        fontSize: 30,
                                        color: const Color.fromARGB(255, 0, 0, 0),
                                      ),
                                    ),
                                  ),
                                ),
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.grey,
                                  child: Center(
                                    child: Text(
                                      "π",
                                      style: TextStyle(
                                        fontSize: 20,
                                        color: const Color.fromARGB(255, 0, 0, 0),
                                      ),
                                    ),
                                  ),
                                ),
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.grey,
                                  child: Center(
                                    child: Text(
                                      "^",
                                      style: TextStyle(
                                        fontSize: 20,
                                        color: Colors.black,
                                      ),
                                    ),
                                  ),
                                ),
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.grey,
                                  child: Center(
                                    child: Text(
                                      "!",
                                      style: TextStyle(
                                        fontSize: 20,
                                        color: Colors.black,
                                      ),
                                    ),
                                  ),
                                ),
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.grey,
                                  child: Center(
                                    child: Text(
                                      ">",
                                      style: TextStyle(
                                        fontSize: 20,
                                        color: Colors.black,
                                      ),
                                    ),
                                  ),
                                ),
                              ],
                            ),
                          ),

                          Expanded(
                            flex: 2,
                            child: GridView.count(
                              crossAxisCount: 3,
                              childAspectRatio: 1.3,
                              children: [
                                for (var i in [7, 8, 9, 4, 5, 6, 1, 2, 3, 0])
                                  Card(
                                    margin: EdgeInsets.all(3),
                                    color: Colors.grey,
                                    child: Center(
                                      child: Text(
                                        "$i",
                                        style: TextStyle(
                                          fontSize: 20,
                                          color: Colors.black,
                                        ),
                                      ),
                                    ),
                                  ),
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.grey,
                                  child: Center(
                                    child: Text(
                                      ".",
                                      style: TextStyle(
                                        fontSize: 20,
                                        color: Colors.black,
                                      ),
                                    ),
                                  ),
                                ),
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.grey,
                                  child: Center(
                                    child: Icon(
                                      Icons.backspace,
                                      color: Colors.black,
                                    ),
                                  ),
                                ),
                              ],
                            ),
                          ),

                          Expanded(
                            flex: 2,
                            child: GridView.count(
                              crossAxisCount: 2,
                              childAspectRatio: 1.9,
                              children: [
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.indigoAccent,
                                  child: Center(
                                    child: Text(
                                      "÷",
                                      style: TextStyle(
                                        fontSize: 20,
                                        color: Colors.black,
                                      ),
                                    ),
                                  ),
                                ),
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.indigoAccent,
                                  child: Center(
                                    child: Text(
                                      "AC",
                                      style: TextStyle(
                                        fontSize: 20,
                                        color: Colors.black,
                                      ),
                                    ),
                                  ),
                                ),
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.indigoAccent,
                                  child: Center(
                                    child: Text(
                                      "X",
                                      style: TextStyle(
                                        fontSize: 20,
                                        color: Colors.black,
                                      ),
                                    ),
                                  ),
                                ),
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.indigoAccent,
                                  child: Center(
                                    child: Text(
                                      "()",
                                      style: TextStyle(
                                        fontSize: 20,
                                        color: Colors.black,
                                      ),
                                    ),
                                  ),
                                ),
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.indigoAccent,
                                  child: Center(
                                    child: Text(
                                      "-",
                                      style: TextStyle(
                                        fontSize: 20,
                                        color: Colors.black,
                                      ),
                                    ),
                                  ),
                                ),
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.indigoAccent,
                                  child: Center(
                                    child: Text(
                                      "%",
                                      style: TextStyle(
                                        fontSize: 20,
                                        color: Colors.black,
                                      ),
                                    ),
                                  ),
                                ),
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.indigoAccent,
                                  child: Center(
                                    child: Text(
                                      "+",
                                      style: TextStyle(
                                        fontSize: 20,
                                        color: Colors.black,
                                      ),
                                    ),
                                  ),
                                ),
                                Card(
                                  margin: EdgeInsets.all(3),
                                  color: Colors.indigoAccent,
                                  child: Center(
                                    child: Text(
                                      "=",
                                      style: TextStyle(
                                        fontSize: 20,
                                        color: Colors.black,
                                      ),
                                    ),
                                  ),
                                ),
                              ],
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
