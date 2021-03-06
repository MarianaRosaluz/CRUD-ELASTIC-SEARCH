package br.rosaluz.banking.system.repository;

import br.rosaluz.banking.system.model.User;
import br.rosaluz.banking.system.model.filter.UserSearchFilter;
import br.rosaluz.banking.system.utils.IndexUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public User saveOrUpdate(final User user) {

        final var userIndex = getUserIndex(user);
        final var userUpdated = elasticsearchOperations.save(user, IndexCoordinates.of(userIndex));
        userUpdated.setIndex(userIndex);
        return userUpdated;
    }

    @Override
    public User searchByIds(final Set<String> ids) {
        final NativeSearchQuery queryID = getNativeSearchQueryById(ids);
        final var indexAlias = IndexCoordinates.of(IndexUtil.getIndexPerWeek());
        final SearchHits<User> userHits = elasticsearchOperations.search(
                queryID,
                User.class,
                indexAlias);

        return userHits.getSearchHits().get(0).getContent();
    }

    @Override
    public void  deleteUser(final Set<String> ids){

        final var query = new BoolQueryBuilder();
        filterById(query, ids.toArray(new String[0]));
        var queryId = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();

        final var indexAlias = IndexCoordinates.of(IndexUtil.getIndexPerWeek());
       var userHits = elasticsearchOperations.delete(queryId,User.class,indexAlias);

    }

    @Override
    public Optional<User> findByLogin(String Login) {
        return Optional.empty();
    }



    @Override
    public Page<User> userSearch(UserSearchFilter userSearchFilter, Pageable pageable) {
        final BoolQueryBuilder query = QueryBuilders.boolQuery();
        buildQuery(userSearchFilter, query);
        final var indexAlias = IndexUtil.getAlias();
        final SearchHits<User>
                orderHits =
                elasticsearchOperations.search(new NativeSearchQueryBuilder().withQuery(query)
                        .withPageable(pageable)
                        .build(), User.class, IndexCoordinates.of(indexAlias));

        return (Page<User>) SearchHitSupport.unwrapSearchHits(SearchHitSupport.searchPageFor(orderHits, pageable));

    }


    private void buildQuery(UserSearchFilter userSearchFilter, BoolQueryBuilder query) {
        Optional.ofNullable(userSearchFilter.getUserId()).ifPresent(value -> filterById(query, "userId.keyword",value));
        Optional.ofNullable(userSearchFilter.getName()).ifPresent(value -> filterEqual(query,"name.keyword", value));
        Optional.ofNullable(userSearchFilter.getCpf()).ifPresent(value -> filterEqual(query,"cpf.keyword", value));

    }

    private void filterEqual(BoolQueryBuilder query, String key, String value) {
        query.must(termQuery(key, value));
    }

    private void filterById(BoolQueryBuilder query, String... value){
        query.filter(QueryBuilders.idsQuery().addIds(value));
    }

    private User getOrderWithIndex(SearchHit<User> userSearchHit) {
        var user = userSearchHit.getContent();
        user.setIndex(userSearchHit.getIndex());
        return user;
    }

    private String getUserIndex(User user) {
        return Objects.isNull(user.getIndex()) || user.getIndex().isEmpty() ? IndexUtil.getIndexPerWeek() : user.getIndex();
    }
    private NativeSearchQuery getNativeSearchQueryById(Set<String> ids) {
        final var query = new BoolQueryBuilder();
        filterById(query, ids.toArray(new String[0]));
        return new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();
    }
}
