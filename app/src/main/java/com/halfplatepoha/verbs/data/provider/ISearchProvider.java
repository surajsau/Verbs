package com.halfplatepoha.verbs.data.provider;

import com.halfplatepoha.verbs.data.models.Verb;

import java.util.List;

public interface ISearchProvider {

    List<Verb> search(String search);

}
