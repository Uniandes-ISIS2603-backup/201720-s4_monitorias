/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

/**
 *
 * @author ms.osorio
 */

@Stateless
@Path("bibliotecas")
@Produces("application/json")
@Consumes("application/json")
public class BibliotecaResource {
    @Inject
    BibliotecaLogic bibliotecaLogic;
}
