package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

import com.example.demo.dto.ApplicationGeoSearch;
import com.example.demo.dto.ApplicationSearch;
import com.example.demo.dto.SimpleQuery;
import com.example.demo.dto.SimpleQuery.Operator;

public class SearchQueryBuilder {

	public static Query search(ApplicationSearch search) {
		List<BoolQueryBuilder> andQueries = new ArrayList<>();

		for (int i = 0; i < search.getQueries().size(); ++i) {
			SimpleQuery sq = search.getQueries().get(i);
			QueryBuilder query = !sq.getField().equals("educationLevel")
					? sq.getValue().startsWith("\"") && sq.getValue().endsWith("\"")
							? QueryBuilders.matchPhraseQuery(sq.getField(), sq.getValue())
							: QueryBuilders.matchQuery(sq.getField(), sq.getValue())
					: QueryBuilders.rangeQuery(sq.getField()).from(sq.getStartValue()).to(sq.getEndValue());

			if (i == 0 || sq.getOperator().equals(Operator.OR)) {
				andQueries.add(QueryBuilders.boolQuery());
			}

			BoolQueryBuilder andQuery = andQueries.get(andQueries.size() - 1);
			if (sq.getNot()) {
				andQuery.mustNot(query);
			} else {
				andQuery.must(query);
			}
		}

		BoolQueryBuilder orQuery = QueryBuilders.boolQuery();
		andQueries.forEach(i -> orQuery.should(i));

		return new NativeSearchQueryBuilder().withQuery(orQuery)
				.withHighlightFields(new HighlightBuilder.Field("firstName"), new HighlightBuilder.Field("lastName"),
						new HighlightBuilder.Field("education"), new HighlightBuilder.Field("cvText"),
						new HighlightBuilder.Field("letterText"))
				.build();
	}

	public static Query geoSearch(ApplicationGeoSearch search) {
		return new CriteriaQuery(new Criteria("location").within(new GeoPoint(search.getLat(), search.getLng()),
				search.getDistance() + search.getUnit()));
	}

}
