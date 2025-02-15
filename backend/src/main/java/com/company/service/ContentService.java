package com.company.service;

import com.company.dto.ContentRequestDTO;
import com.company.dto.ContentResponseDTO;

import java.util.List;

public interface ContentService {

    /**
     * Menambahkan konten baru.
     *
     * @param contentRequestDTO Data konten yang akan ditambahkan
     * @return ContentResponseDTO berisi data konten yang baru ditambahkan
     */
    ContentResponseDTO addContent(ContentRequestDTO contentRequestDTO);

    /**
     * Memperbarui konten yang ada.
     *
     * @param contentId ID konten yang akan diperbarui
     * @param contentRequestDTO Data konten yang akan diperbarui
     * @return ContentResponseDTO berisi data konten yang sudah diperbarui
     */
    ContentResponseDTO updateContent(Long contentId, ContentRequestDTO contentRequestDTO);

    /**
     * Menghapus konten berdasarkan ID.
     *
     * @param contentId ID konten yang akan dihapus
     */
    void deleteContent(Long contentId);

    /**
     * Mendapatkan konten berdasarkan ID.
     *
     * @param contentId ID konten yang dicari
     * @return ContentResponseDTO berisi data konten yang ditemukan
     */
    ContentResponseDTO getContentById(Long contentId);

    /**
     * Mendapatkan semua konten.
     *
     * @return List dari ContentResponseDTO berisi semua konten yang ada
     */
    List<ContentResponseDTO> getAllContents();

    /**
     * Memublikasikan konten berdasarkan ID.
     *
     * @param contentId ID konten yang akan dipublikasikan
     * @return ContentResponseDTO berisi data konten yang sudah dipublikasikan
     */
    ContentResponseDTO publishContent(Long contentId);
}

