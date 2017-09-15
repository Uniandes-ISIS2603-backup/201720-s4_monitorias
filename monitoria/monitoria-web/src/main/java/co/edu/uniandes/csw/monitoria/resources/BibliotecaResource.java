/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.monitoria.resources;

import co.edu.uniandes.csw.monitoria.ejb.BibliotecaLogic;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
