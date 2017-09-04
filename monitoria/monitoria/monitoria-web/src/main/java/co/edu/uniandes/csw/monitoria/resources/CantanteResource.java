/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.ejb.CantanteLogic;
import co.edu.uniandes.csw.monitoria.dtos.CantanteDetailDTO;
import co.edu.uniandes.csw.monitoria.entities.CantanteEntity;
import co.edu.uniandes.csw.monitoria.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.monitoria.persistence.CantantePersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "cantantes".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "cantantes". Al ejecutar la aplicación, el
 * recurso será accesibe a través de la ruta "/api/cantantes"
 *
 * @author ISIS2603
 *
 */
@Path("cantantes")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class CantanteResource {

    @Inject
    CantanteLogic cantanteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(CantantePersistence.class.getName());

    /**
     * POST http://localhost:8080/cantante-web/api/cantantes Ejemplo
     * json: { "name":"Diego", "codigo":"123"}
     *
     * @param cantante correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
 la base de datos y el tipo del objeto java. Ejemplo: { "type":
 "CantanteDetailDTO", "id": 1, "name": "Diego" }
     * @throws BusinessLogicException
     */
    @POST
    public CantanteDetailDTO createCantante(CantanteDetailDTO cantante) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        CantanteEntity cantanteEntity = cantante.toEntity();
        // Invoca la lógica para crear la cantante nueva
        CantanteEntity nuevoCantante = cantanteLogic.createCantante(cantanteEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new CantanteDetailDTO(nuevoCantante);
    }

    /**
     * GET para todas las cantantees.
     * http://localhost:8080/cantante-web/api/cantantes
     *
     * @return la lista de todas las cantantees en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<CantanteDetailDTO> getCantantes() throws BusinessLogicException {
        return listEntity2DetailDTO(cantanteLogic.getCantantes());
    }

    /**
     * GET para un cantante
     * http://localhost:8080/cantante-web/api/cantantes/1
     *
     * @param id corresponde al id de el cantante buscado.
     * @return El cantante encontrado. Ejemplo: { "type": "CantanteDetailDTO",
 "id": 1, "name": "Diego" }
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de el cantante buscado se retorna un 404 con
     * el mensaje.
     */
    @GET
    @Path("{id: \\d+}")
    public CantanteDetailDTO getCantante(@PathParam("id") Long id) throws BusinessLogicException {
        throw  new UnsupportedOperationException("Este servicio no ha sido implementado");
    }

    /**
     * PUT http://localhost:8080/cantante-web/api/cantantes/1 Ejemplo
     * json { "id": 1, "name": "cambio de nombre" }
     *
     * @param id corresponde a el cantante a actualizar.
     * @param cantante corresponde a al objeto con los cambios que se van a
     * realizar.
     * @return El cantante actualizado.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del cantante a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public CantanteDetailDTO updateCantante(@PathParam("id") Long id, CantanteDetailDTO cantante) throws BusinessLogicException {
       throw  new UnsupportedOperationException("Este servicio no ha sido implementado");
    }

    /**
     * DELETE http://localhost:8080/cantante-web/api/cantantes/1
     *
     * @param id corresponde a el cantante a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de el cantante a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCantante(@PathParam("id") Long id) throws BusinessLogicException {
       throw  new UnsupportedOperationException("Este servicio no ha sido implementado");
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos cantanteEntity a una lista de
 objetos CantanteDetailDTO (json)
     *
     * @param entityList corresponde a la lista de cantantes de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de cantantees en forma DTO (json)
     */
    private List<CantanteDetailDTO> listEntity2DetailDTO(List<CantanteEntity> entityList) {
        List<CantanteDetailDTO> list = new ArrayList<>();
        for (CantanteEntity entity : entityList) {
            list.add(new CantanteDetailDTO(entity));
        }
        return list;
    }

}
