package br.com.fiap.globalsolution.resource;

import java.sql.SQLException;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

import br.com.fiap.globalsolution.exception.BadInfoException;
import br.com.fiap.globalsolution.exception.IdNotFoundException;
import br.com.fiap.globalsolution.model.Medico;
import br.com.fiap.globalsolution.service.MedicoService;

@Path("/medicos")//http://localhost:8080/GSApi/api/medicos
public class MedicoResource {

    private MedicoService medicoService;

    public MedicoResource() throws ClassNotFoundException, SQLException, BadInfoException, IdNotFoundException {
        medicoService = new MedicoService();
    }

    //GET - Listar medicos - http://localhost:8080/GSApi/api/medicos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Medico> listarMedicos() throws SQLException, IdNotFoundException {
        return medicoService.listarMedicos();
    }

    //POST - Cadastrar medico - http://localhost:8080/GSApi/api/medicos
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarMedico(Medico medico, @Context UriInfo uri) {
        try {
            medicoService.cadastrarMedico(medico);
            UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
            uriBuilder.path(String.valueOf(medico.getId()));
            return Response.created(uriBuilder.build()).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    //GET - Pesquisar por id - http://localhost:8080/GSApi/api/medicos/id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarMedico(@PathParam("id") int id) {
        try {
            Medico medico = medicoService.pesquisarMedico(id);
            return Response.ok(medico).build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    //DELETE - Remover por id - http://localhost:8080/GSApi/api/medicos/id
    @DELETE
    @Path("/{id}")
    public Response removerMedico(@PathParam("id") int id) {
        try {
            medicoService.removerMedico(id);
            return Response.noContent().build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    //PUT - Atualizar por id - http://localhost:8080/GSApi/api/medicos/id
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarMedico(Medico medico, @PathParam("id") int id) throws ClassNotFoundException, SQLException {
        try {
            medico.setId(id);
            medicoService.atualizarMedico(medico);
            return Response.ok().build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (BadInfoException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (ClassNotFoundException | SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}