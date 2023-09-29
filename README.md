Dificultades
1. Cuando agrego nueva tarea que se muestre en la pantalla automáticamente, pero investigando me encontre con la funcion notifyDataSetChanged() que me ayudaba a realizar lo que yo queria. 
2. Otra dificultad feu agregar la ventana emergente antes de eliminar una tarea. Afortunadamente, el buen youtube me ayudó a resolverlo.
3. Para elimar un elemento de ryceclyview me confundí un poco porque era algo diferente a como lo hice con ListView
4. Poner la imagen en el recycle view. Para que la imagen pudiera verse bien tuve que recomodar todo la estructura de mi item_recycler, ademas que decidi añadir en la clase de tarea que se guarde un referente de la foto aunque siempre sea la misma imagen. 
5. Cuando giraba la pantalla los datos desaparecian, pero lo que estaba pasando era que los datos se "reiniciaban". Lo único que tuve que hacer para arreglar este problema fue cambiar la lista de tareas a un tipo static. 
6. Cuando terminé los requerimientos obligatorios de la tarea decidí realizar el botón de modificar en ambas listas. El principal problema con esto fue el hecho de que no sabía como implementar la idea, pero luego me dí cuenta que lo podía hacer parecido al dialog de eliminar. Reutilizando codigo de la funcion de eliminar y con ayuda de videos y foros pude realizar la tarea de modificar.
