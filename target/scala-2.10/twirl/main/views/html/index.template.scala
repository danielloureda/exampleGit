
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.4*/("""

"""),_display_(/*3.2*/main("Welcome to the User Management application")/*3.52*/ {_display_(Seq[Any](format.raw/*3.54*/("""
	"""),format.raw/*4.2*/("""<script type='text/javascript' src='"""),_display_(/*4.39*/routes/*4.45*/.Assets.at("javascripts/index.js")),format.raw/*4.79*/("""'></script>

	<ul id="persons"></ul>


	<form method="POST" action=""""),_display_(/*9.31*/routes/*9.37*/.Application.addPerson()),format.raw/*9.61*/("""">
		<input type="text" name="name"/>
		<button>Add Person</button>
	</form>
""")))}))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sat Nov 22 09:48:54 CET 2014
                  SOURCE: /Users/Dano/Google Drive/Master/Cloud computing/Cloud/ejemploResuelto/app/views/index.scala.html
                  HASH: 33978222be612f451ff46ec637433c34596f316c
                  MATRIX: 716->1|805->3|833->6|891->56|930->58|958->60|1021->97|1035->103|1089->137|1184->206|1198->212|1242->236
                  LINES: 26->1|29->1|31->3|31->3|31->3|32->4|32->4|32->4|32->4|37->9|37->9|37->9
                  -- GENERATED --
              */
          