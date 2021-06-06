package ar.edu.unahur.obj2.semillasAlViento

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
})