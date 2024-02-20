def parser (arreglo):
    if arreglo[0] != "left" or arreglo[len(arreglo)-1] != "right":
        raise Exception
