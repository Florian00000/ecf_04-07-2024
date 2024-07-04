package com.example.jeejaxrsgestionrh.entitie.map;

import com.example.jeejaxrsgestionrh.entitie.Position;

public class PositionMapper {
    public static Position map (Position position) {
        Position positionDto = new Position();
        positionDto.setId(position.getId());
        positionDto.setName(position.getName());
        return positionDto;
    }
}
