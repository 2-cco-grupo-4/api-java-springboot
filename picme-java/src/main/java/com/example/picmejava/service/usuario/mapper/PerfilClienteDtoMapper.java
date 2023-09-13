package com.example.picmejava.service.usuario.mapper;

import com.example.picmejava.model.Cliente;
import com.example.picmejava.service.usuario.dto.PerfilClienteDTO;

public class PerfilClienteDtoMapper {

        public static PerfilClienteDTO mapClienteToPerfilClienteDTO(Cliente cliente) {
            PerfilClienteDTO perfilClienteDTO = new PerfilClienteDTO();
            perfilClienteDTO.setId(cliente.getId());
            perfilClienteDTO.setNome(cliente.getNome());
            perfilClienteDTO.setTipoUsuario(cliente.getTipoUsuario());
            perfilClienteDTO.setTemas(perfilClienteDTO.getTemas());

            return perfilClienteDTO;
        }

}
