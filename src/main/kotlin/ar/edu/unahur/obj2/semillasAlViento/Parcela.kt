package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {

  /*DESACOPLAMIENTO - pone una variable que contiene informacion dependiente de otra.
  Tambien sabiendo que hay un metodo que nos devuelve esto, podriamos decir que se presenta una redundancia minima*/
  val plantas = mutableListOf<Planta>()
  var cantidadPlantas = 0

  fun superficie() = ancho * largo
  fun cantidadMaximaPlantas() =
    /*REDUNDANCIA MINIMA - estamos haciendo una operacion (ancho * largo) que ya tenemos hecha en otra funcion reutilizable (superficie)*/
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo

  fun plantar(planta: Planta) {
    /*ROBUSTEZ - deberia tirar un error en lugar de imprimir en consola. ya que el usuario podria ir agregando mas y mas plantas sin darse cuenta de que está fallando*/
    if (cantidadPlantas == this.cantidadMaximaPlantas()) {
      println("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      println("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
      cantidadPlantas += 1
    }
  }
}

class Agricultora(val parcelas: MutableList<Parcela>) {

  /*SIMPLICIDAD - la agricultora no pueden vender ni comprar parcelas. tener la cuenta de los ahorros y un metodo para comprar es absolutamente innecesario.
* Por lo tante estamos en presencia de un YAGNI*/
  var ahorrosEnPesos = 20000
  // Suponemos que una parcela vale 5000 peso
  fun comprarParcela(parcela: Parcela) {
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  }

  fun parcelasSemilleras() =

    /*MUTACION CONTROLADA - esta haciendo varias interaciones en la lista haciendo el codigo menos legible*/
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }

  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!
    laElegida.plantas.add(planta)
  }
}
