package com.reksoft.exporter.service;

import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.repository.PlayerApiRepository;
import com.reksoft.exporter.repository.dto.PlayerViewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerApiRepository playerApiRepository;

    @Override
    public List<Player> getPlayers() {
        List<PlayerViewDto> playerViewDtos = playerApiRepository.getPlayers();
        return playerViewDtos.stream().map(this::map).toList();
    }

    private Player map(PlayerViewDto playerViewDto) {
        Player player = new Player();
        player.setId(playerViewDto.getId());
        player.setCountry(player.getCountry());
        player.setNickname(playerViewDto.getCombinedName());
        player.setFullName(playerViewDto.getCombinedName());
        player.setTeamName(playerViewDto.getTeamName());
        return player;
    }
}
