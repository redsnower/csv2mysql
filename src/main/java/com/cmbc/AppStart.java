package com.cmbc;

import com.cmbc.utils.ImportCVSUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class AppStart
{
    public static void main( String[] args ) throws FileNotFoundException {
        ImportCVSUtil.importToMysql();
    }


}
