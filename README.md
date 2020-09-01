# VoteForRestaurant
Voting system for deciding where to have lunch. REST API using Hibernate/Spring/SpringMVC without frontend.

#### add a restaurant
`curl -X POST -d '{"name": "new Restaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/voteforrestaurant/rest/admin/restaurants`
