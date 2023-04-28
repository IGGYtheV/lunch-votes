

docker postgres container generation:
docker run -p 5432:5432 -d --name lunchvotes_db -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=lunchvotes postgres