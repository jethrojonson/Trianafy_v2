package com.salesianos.triana.dam.trianafy.playlist.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

public interface PlaylistViews {

    public class NewPlaylist{}

    public class SimplePlaylist extends NewPlaylist{}

    public class SimplePlaylist2{}

    public class FullPlaylist extends SimplePlaylist{}
}
