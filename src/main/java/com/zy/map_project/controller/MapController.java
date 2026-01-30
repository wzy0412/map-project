package com.zy.map_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MapController {

    @GetMapping("/convert")
    public Map<String, Object> convert(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam int zoom
    ){
        double mapSize = 256*Math.pow(2, zoom);
        double x=(lon+180.0) / 360.0*mapSize;
        double sinLatitude = Math.sin(lat*Math.PI / 180.0);
        double y= (0.5-Math.log((1+sinLatitude)/(1-sinLatitude))/(4*Math.PI))*mapSize;

        int tileX=(int)Math.floor(x/256);
        int tileY=(int)Math.floor(y/256);

        return Map.of(
                "pixelX",x,
                "pixelY",y,
                "tileX", tileX,
                "tileY", tileY,
                "zoom",zoom,
                "location","Calculated by zy's Server"
        );
    }

}
