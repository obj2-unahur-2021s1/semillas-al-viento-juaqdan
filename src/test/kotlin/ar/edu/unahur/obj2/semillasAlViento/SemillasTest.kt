package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class SemillaTest : DescribeSpec({
    val menta = Menta(2020, 0.3f)
    val mentaGrande = Menta(2020, 0.6f)

    val sojaNormal = Soja(2019, 0.4f )
    val sojaTransgenica = SojaTransgenica(2019, 0.6f )

    describe ("Plantas") {
        describe ("Horas de sol que tolera") {
            it ("Menta") {
                menta.horasDeSolQueTolera().shouldBe(6)
            }
            it ("Soja") {
                sojaNormal.horasDeSolQueTolera().shouldBe(6)
                sojaTransgenica.horasDeSolQueTolera().shouldBe(14)
            }

        }

        describe ("Es fuerte?") {
            it ("Menta") {
                menta.esFuerte().shouldBeFalse()
            }
            it ("Soja") {
                sojaNormal.esFuerte().shouldBeFalse()
                sojaTransgenica.esFuerte().shouldBeTrue()
            }
        }

        describe ("Da semillas") {
            it ("Menta") {
                menta.daSemillas().shouldBeFalse()
                mentaGrande.daSemillas().shouldBeTrue()
            }
            it("soja"){
                sojaNormal.daSemillas().shouldBeFalse()
                sojaTransgenica.daSemillas().shouldBeFalse()
            }
        }
    }


    val parcela = Parcela(2, 3, 7)
    val parcelaGrande = Parcela(20,10, 8)

    describe ("Parcelas") {
        it ("Superfice") {
            parcela.superficie().shouldBe(6)
            parcelaGrande.superficie().shouldBe(200)
        }
        it ("Plantas que tolera") {
            parcela.cantidadMaximaPlantas().shouldBe(5)
            parcelaGrande.cantidadMaximaPlantas().shouldBe(40)
        }
      /*  it ("Tiene complicaciones?") {
            parcela.plantar(sojaNormal)
            parcelaGrande.plantar(menta)

            parcela.tieneComplicaciones().shoulBeTrue()
            parcelaGrande.tieneComplicaciones().shoulBeFalse()
        } */

        it ("Plantar") {
            parcela.plantar(sojaNormal)
            parcela.plantar(sojaTransgenica)
            parcela.plantar(menta)
            parcela.plantar(mentaGrande)


          // shouldThrowAny { parcela.plantar(mentaGrande) }
        }
    }

    describe("agricultoras") {
        parcela.plantar(mentaGrande)
        parcelaGrande.plantar(sojaTransgenica)
        val agricultora = Agricultora(mutableListOf<Parcela>(parcela, parcelaGrande))
        val agricultora2 = Agricultora(mutableListOf<Parcela>(parcela, parcela))

        val parcelaComun = Parcela(15, 1, 5)
        val agricultora3 = Agricultora(mutableListOf<Parcela>(parcela, parcelaComun))

        it("parcelas semilleras"){
            agricultora.parcelasSemilleras().shouldBe(listOf(parcela))
            agricultora2.parcelasSemilleras().shouldBe(listOf(parcela, parcela))
        }
        it("plantar estrategicamente"){
            agricultora3.plantarEstrategicamente(Soja(2018, 0.5f))
            agricultora3.plantarEstrategicamente(Soja(2018, 0.5f))
            agricultora3.plantarEstrategicamente(Soja(2018, 0.5f))
            agricultora3.plantarEstrategicamente(Soja(2018, 0.5f))
            agricultora3.plantarEstrategicamente(Soja(2018, 0.5f))
            agricultora3.plantarEstrategicamente(Soja(2018, 0.5f))
            agricultora3.plantarEstrategicamente(Soja(2018, 0.5f))
            agricultora3.plantarEstrategicamente(Soja(2018, 0.5f))

            //shouldThrowAny { agricultora3.plantarEstrategicamente(Soja(2018, 0.5f)) }
        }
    }

})