package com.github.maliqq.euler

import scala.reflect.runtime.{ universe => ru }
import scala.reflect.runtime.{ currentMirror => cm }

object Main {
  def main(args: Array[String]) {
    val n = 11
    
    val solutions = cm.reflect(Problems).symbol.typeSignature.declarations.filter(_.isModule)
    val solution = solutions.find { decl =>
      decl.annotations.exists { annot =>
        val number = annot.scalaArgs.productElement(0).asInstanceOf[ru.Literal].value
        number.value.asInstanceOf[Int] == n
      }
    }

    if (solution.isDefined) {
      cm.reflectModule(solution.get.asModule).instance.asInstanceOf[Runnable].run()
    }
  }
}
