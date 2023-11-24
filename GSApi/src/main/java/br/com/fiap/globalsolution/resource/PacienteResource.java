package br.com.fiap.globalsolution.resource;

import java.sql.SQLException;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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
import br.com.fiap.globalsolution.model.Paciente;
import br.com.fiap.globalsolution.service.PacienteService;

@Path("/pacientes") //http://localhost:8080/GSApi/api/pacientes
public class PacienteResource {

    private PacienteService service;

    public PacienteResource() throws ClassNotFoundException, SQLException, BadInfoException, IdNotFoundException {
        service = new PacienteService();
    }

    //GET - Listar pacientes - http://localhost:8080/GSApi/api/pacientes
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paciente> listarPacientes() throws SQLException, IdNotFoundException {
        return service.listarPacientes();
    }
    //POST - Cadastrar paciente - http://localhost:8080/GSApi/api/pacientes
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarPaciente(Paciente paciente, @Context UriInfo uri) {
        try {
            service.cadastrarPaciente(paciente);
            UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
            uriBuilder.path(String.valueOf(paciente.getId()));
            return Response.created(uriBuilder.build()).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    //GET - Pesquisar por id - http://localhost:8080/GSApi/api/pacientes/id
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pesquisarPaciente(@PathParam("id") int id) {
        try {
            Paciente paciente = service.pesquisarPaciente(id);
            return Response.ok(paciente).build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    //DELETE - Deletar por id - http://localhost:8080/GSApi/api/pacientes/id
    @DELETE
    @Path("/{id}")
    public Response removerPaciente(@PathParam("id") int id) {
        try {
            service.removerPaciente(id);
            return Response.noContent().build();
        } catch (IdNotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        } catch (SQLException e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    //PUT - Atualizar por id - http://localhost:8080/GSApi/api/pacientes/id
    @PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Paciente paciente, @PathParam("id") int codigo) throws ClassNotFoundException, SQLException {
		try {
			paciente.setId(codigo);
			service.atualizarPaciente(paciente);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
    
}
