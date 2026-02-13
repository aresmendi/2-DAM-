import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: TodosPage(),
      debugShowCheckedModeBanner: false,
    );
  }
}

class TodosPage extends StatelessWidget {
  const TodosPage({super.key});
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => TodosNotifier(),
      builder: (context, provider) {
        return Consumer<TodosNotifier>(
          builder: (context, notifier, child) {
            return Scaffold(
              appBar: AppBar(title: const Text('Tareas')),
              body: ListView.builder(
                itemCount: notifier.todos.length,
                itemBuilder: (context, index) {
                  final todo = notifier.todos[index];
                  return ListTile(
                    title: Text(todo.title),
                    trailing: Checkbox(
                      value: todo.completed,
                      onChanged: (value) {
                        notifier.toggle(todo.id);
                      },
                    ),
                    onLongPress: () {
                      notifier.remove(todo.id);
                    },
                  );
                },
              ),
              floatingActionButton: FloatingActionButton(
                onPressed: () {
                  showDialog(
                    context: context,
                    builder: (BuildContext context) {
                      return NewTodoDialog(notifier: notifier);
                    },
                  );
                },
                child: const Icon(Icons.add),
              ),
            );
          },
        );
      },
    );
  }
}

class NewTodoDialog extends StatefulWidget {
  const NewTodoDialog({super.key, required this.notifier});
  final TodosNotifier notifier;
  @override
  State<NewTodoDialog> createState() => _NewTodoDialogState();
}

class _NewTodoDialogState extends State<NewTodoDialog> {
  get notifier => widget.notifier;
  final _textEditingController = TextEditingController();
  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: const Text('Crear tarea'),
      content: TextField(
        controller: _textEditingController,
        decoration: const InputDecoration(hintText: "nueva tarea..."),
      ),
      actions: <Widget>[
        TextButton(
          child: const Text('Cancelar'),
          onPressed: () {
            Navigator.of(context).pop();
          },
        ),
        TextButton(
          child: const Text('Crear'),
          onPressed: () {
            notifier?.add(
              Todo(
                id: DateTime.now().toString(),
                title: _textEditingController.text,
              ),
            );
            Navigator.pop(context);
          },
        ),
      ],
    );
  }
}

class TodosNotifier extends ChangeNotifier {
  List<Todo> todos = [];
  void add(Todo todo) {
    todos.add(todo);
    notifyListeners();
  }

  void remove(String todoId) {
    todos.removeWhere((todo) => todo.id == todoId);
    notifyListeners();
  }

  void toggle(String todoId) {
    final todo = todos.firstWhere((todo) => todo.id == todoId);
    todo.completed = !todo.completed;
    notifyListeners();
  }
}

class Todo {
  Todo({required this.id, required this.title, this.completed = false});
  final String id;
  final String title;
  bool completed;
}
