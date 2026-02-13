import 'package:flutter/material.dart';
import 'welcome_screen.dart';
void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});
  @override
  Widget build(BuildContext context) {
    return  MaterialApp(
      title: 'IMC',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.red),
      ),
      debugShowCheckedModeBanner: false,
      home: WelcomeScreen(),
    );
  }
}
