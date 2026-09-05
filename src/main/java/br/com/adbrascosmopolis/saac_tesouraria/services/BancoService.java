package br.com.adbrascosmopolis.saac_tesouraria.services;

import br.com.adbrascosmopolis.saac_tesouraria.dto.banco.BancoDropdownDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.banco.BancoEditDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.banco.BancoRequestDTO;
import br.com.adbrascosmopolis.saac_tesouraria.dto.banco.BancoResponseDTO;
import br.com.adbrascosmopolis.saac_tesouraria.models.Banco;
import br.com.adbrascosmopolis.saac_tesouraria.repository.BancoRepository;
import br.com.adbrascosmopolis.saac_tesouraria.repository.SituacaoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BancoService {

    private final BancoRepository bancoRepository;
    private final SituacaoRepository situacaoRepository;

    public BancoService(
            BancoRepository bancoRepository,
            SituacaoRepository situacaoRepository
    ){
        this.bancoRepository = bancoRepository;
        this.situacaoRepository = situacaoRepository;
    }

    //Dropdown Bancos
    public List<BancoDropdownDTO> dropdownBancos(){
        List<BancoDropdownDTO> bancos = bancoRepository.listarBancosDropdown();
        return bancos;
    }

    //Create
    @Transactional
    public BancoResponseDTO create(BancoRequestDTO dto){

        Banco banco = new Banco();

        banco.setCodigoBanco(dto.getCodigoBanco());
        banco.setNome(dto.getNome());

        //Relacionamentos
        if(dto.getSituacaoId() != null){
            banco.setSituacao(
                    situacaoRepository.findById(dto.getSituacaoId())
                            .orElseThrow( () -> new RuntimeException("Situação não encontrada!") )
            );
        }

        Banco salvo = bancoRepository.save(banco);
        Banco completo = bancoRepository.findById(salvo.getBancoId())
                .orElseThrow( () -> new RuntimeException("Banco não encontrado!") );

        return BancoResponseDTO.fromBanco(completo);
    }

    //Update
    @Transactional
    public BancoResponseDTO update(BancoEditDTO dto){

        if(dto.getBancoId() == null){
            throw new RuntimeException("O atributo bancoId não pode ser null!");
        }

        Banco result = bancoRepository.findById(dto.getBancoId())
                .orElseThrow( () -> new RuntimeException("Banco não encontrado!") );

        //Atribuindo novos valores
        result.setCodigoBanco(dto.getCodigoBanco());
        result.setNome(dto.getNome());

        //Relacionamentos
        if(dto.getSituacaoId() != null){
            result.setSituacao(
                    situacaoRepository.getReferenceById(dto.getSituacaoId())
            );
        }

        var bancoAtualizado = bancoRepository.save(result);

        return BancoResponseDTO.fromBanco(bancoAtualizado);
    }

    //Delete
    @Transactional
    public BancoResponseDTO delete(BancoEditDTO dto){

        if(dto.getBancoId() == null){
            throw new RuntimeException("O atributo bancoId não pode ser null!");
        }

        Banco result = bancoRepository.findById(dto.getBancoId())
                .orElseThrow( () -> new RuntimeException("Banco não encontrado!") );

        //Aplicanto Softdelet
        result.setDeletedAt(LocalDate.now());

        //Relacionamentos
        if(dto.getSituacaoId() != null){
            result.setSituacao(
                    situacaoRepository.getReferenceById(dto.getSituacaoId())
            );
        }

        var bancoAtualizado = bancoRepository.save(result);

        return BancoResponseDTO.fromBanco(bancoAtualizado);
    }

}
