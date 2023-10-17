import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

int cont = 1;

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      title: 'Diseño en Flutter con Formato',
      home: PaginaInicial(),
    );
  }
}

class PaginaInicial extends StatefulWidget {
  const PaginaInicial({super.key});

  @override
  State<PaginaInicial> createState() => _PaginaInicialState();
}


class _PaginaInicialState extends State<PaginaInicial> {
  String _texto = 'Hola, Mundo!';

  _cambiarTexto() {
    setState(() {
      if (cont == 0) {
        _texto = 'Hola, Mundo!';
        cont++;
      } else {
        _texto = 'Adios, Mundo!';
        cont--;
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Diseño Basico en Flutter'),
        actions: [Icon(Icons.star)],
      ),
      body: _buildContent(),
    );
  }

  Widget _buildContent() {
    return Container(
      padding: EdgeInsets.all(16.0),
      child: Column(
        children: [
          Text(
            _texto,
            style: const TextStyle(fontSize: 24.0, color: Colors.red),
          ),
          const SizedBox(height: 20.0),
          ElevatedButton(
            onPressed: _cambiarTexto,
            style: ElevatedButton.styleFrom(
              backgroundColor: Colors.blue,
              foregroundColor: Colors.white,
            ),
            child: const Text('Cambiar Texto'),
          ),
          const SizedBox(height: 20.0),
          const Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Expanded(
                child: Image(
                  image: NetworkImage('https://avatars.githubusercontent.com/u/109951?s=400&v=4'),
                  height: 140,
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }

}