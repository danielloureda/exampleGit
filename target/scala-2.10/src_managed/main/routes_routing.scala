// @SOURCE:/Users/Dano/Google Drive/Master/Cloud computing/Cloud/ejemploResuelto/conf/routes
// @HASH:a5163e3be9d0545d208b7fcc4d2ebcf7f1107005
// @DATE:Sat Nov 22 09:48:51 CET 2014


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_index0_invoker = createInvoker(
controllers.Application.index(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
        

// @LINE:7
private[this] lazy val controllers_Application_addPerson1_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("person"))))
private[this] lazy val controllers_Application_addPerson1_invoker = createInvoker(
controllers.Application.addPerson(),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "addPerson", Nil,"POST", """""", Routes.prefix + """person"""))
        

// @LINE:8
private[this] lazy val controllers_Application_deletePerson2_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("person/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
private[this] lazy val controllers_Application_deletePerson2_invoker = createInvoker(
controllers.Application.deletePerson(fakeValue[Long]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "deletePerson", Seq(classOf[Long]),"POST", """""", Routes.prefix + """person/$id<[^/]+>/delete"""))
        

// @LINE:9
private[this] lazy val controllers_Application_getPersons3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("persons"))))
private[this] lazy val controllers_Application_getPersons3_invoker = createInvoker(
controllers.Application.getPersons,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "getPersons", Nil,"GET", """""", Routes.prefix + """persons"""))
        

// @LINE:12
private[this] lazy val controllers_Assets_at4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at4_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """person""","""controllers.Application.addPerson()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """person/$id<[^/]+>/delete""","""controllers.Application.deletePerson(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """persons""","""controllers.Application.getPersons"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0_route(params) => {
   call { 
        controllers_Application_index0_invoker.call(controllers.Application.index())
   }
}
        

// @LINE:7
case controllers_Application_addPerson1_route(params) => {
   call { 
        controllers_Application_addPerson1_invoker.call(controllers.Application.addPerson())
   }
}
        

// @LINE:8
case controllers_Application_deletePerson2_route(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_Application_deletePerson2_invoker.call(controllers.Application.deletePerson(id))
   }
}
        

// @LINE:9
case controllers_Application_getPersons3_route(params) => {
   call { 
        controllers_Application_getPersons3_invoker.call(controllers.Application.getPersons)
   }
}
        

// @LINE:12
case controllers_Assets_at4_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at4_invoker.call(controllers.Assets.at(path, file))
   }
}
        
}

}
     