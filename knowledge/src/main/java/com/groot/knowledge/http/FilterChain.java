package com.groot.knowledge.http;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FilterChain implements BaseFilter {

    private List<BaseFilter> filters = new ArrayList<>();
    private AtomicInteger index = new AtomicInteger(-1);

    public BaseFilter add(BaseFilter filter) {
        this.filters.add(filter);
        return this;
    }

    /**
     * 过滤请求
     * @param m
     * @param filterChain
     * @param processflag
     * @param <R>
     * @param <M>
     * @return
     * @throws Exception
     */
    public <R, M> R process(M m, FilterChain filterChain, boolean processflag) throws Exception {
        index.incrementAndGet();
        if(index.get() == filters.size()) {
            return (R) m;
        }
        m = filters.get(index.get()).process(m,filterChain,processflag);
        return (R) m;
    }
}
