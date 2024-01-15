# Use the official PostgreSQL image from Docker Hub
FROM postgres:15-alpine

# Set environment variables for PostgreSQL
ENV POSTGRES_DB=patient_management_system
ENV POSTGRES_USER=pguser
ENV POSTGRES_PASSWORD=pgpassword

# Expose the default PostgreSQL port
EXPOSE 5432

# Build docker image
# docker build -t tag_image .
# Run the docker container
# docker run -p 5432:5432 --name nom_conteneur -d (detached mode )
