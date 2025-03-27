#language: es
@testfeature
Característica: Login
  Yo, como usuario
  Quiero, tener una opción para iniciar sesión
  Para ver todos los items

  @caso_1
  Escenario: Iniciar sesión
    Dado que me encuentro en la página de la tienda qlab
    Cuando inicio sesión con mi usuario: "jlmunozf.dev@gmail.com" y clave: "MM123456__"

  @caso_2
  Escenario: Intento de inicio de sesión
    Dado que me encuentro en la página de la tienda qlab
    Cuando inicio sesión con mi usuario: "xxx" y clave: "xxx"






