package com.mysmarthome.domain;

import java.util.List;

public record PagedView<T>(List<T> data, Long count, Integer totalPages) {
}
