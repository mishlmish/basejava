package com.urise.webapp.model;

import static java.util.Objects.requireNonNull;
public class TextSection extends Section {
    private  final String content;

    public TextSection(String content) {
        this.content = requireNonNull(content, "content can not be null");
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return content;
    }

//    @Override
//    public  boolean equals(Object o) {
//        if(this == o) return  true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        TextSection that = (TextSection) o;
//
//        return content.equals(that.content);
//    }

//    @Override
//    public int hashCode() {
//        return content.hashCode();
//    }

}
