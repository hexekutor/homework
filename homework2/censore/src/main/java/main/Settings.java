package main;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.kohsuke.args4j.Option;

import java.io.Serializable;

public class Settings implements Serializable {

    @Option(name = "--n", usage = "Set number of censored words", required = true)
    private Integer n;
    @Option(name = "--start", usage = "Set starting word", required = true)
    private Integer start;
    @Option(name = "--size", usage = "Set word size", required = true)
    private Integer size;



    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
