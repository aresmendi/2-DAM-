import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => SettingsScreenNotifier(),
      builder: (context, provider) {
        return Consumer<SettingsScreenNotifier>(
          builder: (context, notifier, child) {
            return MaterialApp(
              debugShowCheckedModeBanner: false,
              title: 'State Example',
              theme: ThemeData(primarySwatch: Colors.blue),
              darkTheme: ThemeData.dark(),
              themeMode: notifier.isDarkModeEnabled
                  ? ThemeMode.dark
                  : ThemeMode.light,
              home: const MyHomePage(title: 'State Example'),
            );
          },
        );
      },
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;
  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: [
          IconButton(
            icon: Icon(Icons.settings),
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const SettingsScreen()),
              );
            },
          ),
        ],
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'State Example',
              style: Theme.of(context).textTheme.headlineMedium,
            ),
          ],
        ),
      ),
    );
  }
}

class SettingsScreen extends StatelessWidget {
  const SettingsScreen({super.key});
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Settings')),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        children: [
          Consumer<SettingsScreenNotifier>(
            builder: (context, notifier, child) {
              return SwitchListTile(
                title: const Text('Dark Mode'),
                value: notifier.isDarkModeEnabled,
                secondary: const Icon(
                  Icons.dark_mode,
                  color: Color(0xFF642ef3),
                ),
                onChanged: (bool value){
                  notifier.toggleApplicationTheme(value);
                },
              );
            },
          ),
        ],
      ),
    );
  }
}

class SettingsScreenNotifier extends ChangeNotifier {
  bool _isDarkModeEnabled = false;
  get isDarkModeEnabled => _isDarkModeEnabled;
  void toggleApplicationTheme(bool darkModeEnabled) {
    _isDarkModeEnabled = darkModeEnabled;
    notifyListeners();
  }
}
