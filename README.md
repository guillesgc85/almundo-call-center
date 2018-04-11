# almundo-call-center

## Descripción

Este proyecto fue diseño e implemento utilizando una arquitectura basada en microservicio, así mismo se utilizó
patrones de diseño y arquitectonicos como mensajes a través de colas de mensajes asíncronas,
las solicitudes de llamadas (*RequestCall*)  son adicionadas a la cola de mensajes llama CallRequestQueue,
las cuales son recibidas a través  de la clase *RequestCallReceiver* vía petición *POST* y posteriormente procesados por el servicio dispatcher,
el servicio dispatcher es el encargado de realizar el manejo y asignación de llamadas a los empleados que se encuentren
con estados FREE, este proceso utiliza el concepto de futuros a través de la interface: *Future* y su implementación: *CompletableFuture* con esto
evitamos posibles inconveniente de concurrencia.


La clase *CallCenterApplication*, es la clase principal utilizada para desplegar el microservicio.

Puede hacer peticiones tipo POST con su herramienta preferida (por ejemplo: postman , soapui),

Tipo POST y formato JSON como la siguiente:

url: *http://localhost:8020/call/send*

JSON:
{
	"call":{
		"messageContent":"new call"
	}
}

Nota: Cuando el numero de llamadas supera el numero de hilos soportada (10), es decir que esto sucede cuando
todos los empleados se encuentran atendiendo llamadas, en estos casos, las llamadas que aún no han sido constestadas
serán encoladas nuevamente y se reintentará el proceso para responder la llamada hasta que un empleado se encuentre con estado FREE
y el sistema le pueda asignar un empleado a la llamadaa.

## Framework y tecnologías.

*Spring boot*
*Spring activemq*
*Junit*
*Java: 1.8*

