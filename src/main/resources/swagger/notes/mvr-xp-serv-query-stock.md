# Implementation Notes

### Acerca de la funcionalidad expuesta
Microservicio de experiencia que se encarga de la consulta del stock

**Path parameters** = No aplica.

<br/>

### URI de acceso a la API
| Método |  					 URI 	  															 |
|--------|-------------------------------------------------------------------------------------------|
|  POST   | http://172.19.189.100:30281/mvr-xp-serv-query-stock/v1/check/stock |

<br/>

### Precondiciones para el consumo de esta versión de la API
No aplica
<br/>

### Headers
**Request**

| 	  Header   | Ejemplo 						 		  | Obligatorio | 
|--------------|------------------------------------------|-------------|
| request-ID   |   75925eb4-5128-4e7d-9f55-683479274b48   | 	 S     	|
| request-date |   2007-11-03T16:18:05Z  				  |  	 S 		|
| app-code	   |   CL  						              |  	 S  	|
<br/>

**Response**

| 	  Header   | Ejemplo 						 		  | Obligatorio | 
|--------------|------------------------------------------|-------------|
| latency   	   |   3455.0   							  | 	 S     	|
| request-ID 	   |   75925eb4-5128-4e7d-9f55-683479274b48   |  	 S 		|
| responseDate	   |   2020-04-15T12:42:49Z-05:00 			  |  	 S  	|
| versionHttp	   |   HTTP/1.1  						      |  	 S  	|
| Content-Type	   |   application/stream+json;charset=UTF-8  |  	 S  	|

<br/>

### Lista de Valores usadas en esta versión de la API
No aplica

<br/>

### Código de errores del Servicio en esta versión de la API
| Código  | Status  |          Descripción         	| 
|---------|---------|-------------------------------|
| IDFF1   |   412  	|  No se pudo ejecutar la consulta   	|
| IDFF2   |   412  	|  No se pudo ejecutar la consulta      |
| IDTT1   |   500  	|  Servicio no se encuentra disponible	|

<br/>