from faker import Faker
from pymongo import MongoClient

client = MongoClient('mongodb://localhost:27017/')
db = client['users']
collection = db['users']

fake = Faker()

def generate_fake_data():
    return {
        "Name:": fake.name(),
        "Address:": fake.address(),
        "Email:": fake.email(),
        "Phone:": fake.phone_number(),
        "Company:": fake.company(),
        "Country:": fake.country()
    }

def insert_fake_data():
    for _ in range(1):
        fake_data = generate_fake_data()
        collection.insert_one(fake_data)
        print(f"Data added: {fake_data}")


insert_fake_data()