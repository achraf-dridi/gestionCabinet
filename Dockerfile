# Use the official PostgreSQL image from Docker Hub
FROM postgres:latest

# Set environment variables for PostgreSQL
ENV POSTGRES_DB=gestionPatient
ENV POSTGRES_USER=root
ENV POSTGRES_PASSWORD=root

# Expose the default PostgreSQL port
EXPOSE 5432

# Build docker image
# docker build tag_image .
# Run the docker container
# docker run -p 5432:5432 --name nom_conteneur -d (detached mode )
