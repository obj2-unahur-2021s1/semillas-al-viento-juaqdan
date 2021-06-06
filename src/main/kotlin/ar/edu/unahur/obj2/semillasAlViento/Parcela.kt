package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {

  val plantas = mutableListOf<Planta>()


  fun superficie() = ancho * largo

  fun cantidadMaximaPlantas() = if (ancho > largo) superficie() / 5 else superficie() / 3 + largo

  fun tieneComplicaciones() = plantas.any { it.horasDeSolQueTolera() < horasSolPorDia }

  fun plantar(planta: Planta) {
    check(plantas.size < this.cantidadMaximaPlantas()) {
      "Ya no hay lugar en esta parcela"
    }
    check ( planta.horasDeSolQueTolera() + 2 > horasSolPorDia ) {
      "No se puede plantar esto acá, se va a quemar"
    }

    plantas.add(planta)

  }

  fun esSemillera() = plantas.all { it.daSemillas() }
}

class Agricultora(val parcelas: MutableList<Parcela>) {


  fun parcelasSemilleras() = parcelas.filter { it.esSemillera()}

  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxByOrNull { it.cantidadMaximaPlantas() - it.plantas.size }//Ver para un default
    (laElegida ?: parcelas.first()).plantar(planta)
  }
}
