#language: es
@testfeature
Característica: Login
  Yo, como usuario
  Quiero, tener una opción para iniciar sesión
  Para ver todos los items


  Esquema del escenario: Credenciales incorrectas
    Dado que me encuentro en la página de la tienda qlab
    Y me logueo con mi usuario: "<usuario>" y clave "<clave>"
    Entonces imprimo el mensaje de credenciales incorrectas

    Ejemplos:
      | usuario                | clave |
      | jlmunozf.dev@gmail.com | 1234  |

  Esquema del escenario: Credenciales correctas pero no existe la categoria
    Dado que me encuentro en la página de la tienda qlab
    Y me logueo con mi usuario: "<usuario>" y clave "<clave>"
    Cuando navego hacia la categoría: "<categoria>" y subcategoría "<sub_categoria>"
    Entonces imprimo el mensaje de categoria no encontrada

    Ejemplos:
      | usuario                | clave      | categoria | sub_categoria |
      | jlmunozf.dev@gmail.com | MM123456__ | Autos     | Men           |


  Esquema del escenario: Validación del precio de un producto con usuario y contraseña válidos
    Dado que me encuentro en la página de la tienda qlab
    Y me logueo con mi usuario: "<usuario>" y clave "<clave>"
    Cuando navego hacia la categoría: "<categoria>" y subcategoría "<sub_categoria>"
    Y agrego 2 unidades del primer producto al carrito
    Entonces valido en el popup la confirmación del producto agregado
    Cuando finalizo la compra
    Entonces valido el titulo de la pagina del carrito
    Y vuelvo a validar el calculo de precios en el carrito

    Ejemplos:
      | usuario                | clave      | categoria | sub_categoria |
      | jlmunozf.dev@gmail.com | MM123456__ | CLOTHES   | Men           |




