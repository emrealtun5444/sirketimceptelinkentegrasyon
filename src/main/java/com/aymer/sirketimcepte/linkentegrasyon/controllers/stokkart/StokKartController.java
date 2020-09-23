package com.aymer.sirketimcepte.linkentegrasyon.controllers.stokkart;

import com.aymer.sirketimcepte.linkentegrasyon.dto.StokKartDto;
import com.aymer.sirketimcepte.linkentegrasyon.repository.StokKartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:00
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/stokKart")
public class StokKartController {

    private StokKartRepository stokKartRepository;

    @Autowired
    public StokKartController(StokKartRepository stokKartRepository) {
        this.stokKartRepository = stokKartRepository;
    }

    @GetMapping("/{stokKodu}")
    public ResponseEntity<?> stokKartById(@Valid @PathVariable(name = "stokKodu") String stokKodu) {
        StokKartDto stokKartDto = stokKartRepository.findByStokKodu(stokKodu);
        return ResponseEntity.ok(stokKartDto);
    }


}
