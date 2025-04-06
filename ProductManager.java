import sqlite3

def connect_db():
    return sqlite3.connect("products.db")

def create_table(conn):
    with conn:
        conn.execute("""
            CREATE TABLE IF NOT EXISTS Product (
                ProductID INTEGER PRIMARY KEY,
                ProductName TEXT NOT NULL,
                Price REAL NOT NULL,
                Quantity INTEGER NOT NULL
            )
        """)

def create_product(conn):
    try:
        product_name = input("Enter Product Name: ")
        price = float(input("Enter Price: "))
        quantity = int(input("Enter Quantity: "))
        with conn:
            conn.execute("INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)",
                         (product_name, price, quantity))
        print("Product created successfully.")
    except Exception as e:
        conn.rollback()
        print(f"Error creating product: {e}")

def read_products(conn):
    cursor = conn.execute("SELECT * FROM Product")
    rows = cursor.fetchall()
    if rows:
        print("\n--- Product List ---")
        for row in rows:
            print(f"ID: {row[0]}, Name: {row[1]}, Price: {row[2]}, Quantity: {row[3]}")
    else:
        print("No products found.")

def update_product(conn):
    try:
        product_id = int(input("Enter Product ID to update: "))
        product_name = input("Enter new Product Name: ")
        price = float(input("Enter new Price: "))
        quantity = int(input("Enter new Quantity: "))
        with conn:
            cursor = conn.execute("UPDATE Product SET ProductName = ?, Price = ?, Quantity = ? WHERE ProductID = ?",
                                  (product_name, price, quantity, product_id))
            if cursor.rowcount == 0:
                print("Product ID not found.")
            else:
                print("Product updated successfully.")
    except Exception as e:
        conn.rollback()
        print(f"Error updating product: {e}")

def delete_product(conn):
    try:
        product_id = int(input("Enter Product ID to delete: "))
        with conn:
            cursor = conn.execute("DELETE FROM Product WHERE ProductID = ?", (product_id,))
            if cursor.rowcount == 0:
                print("Product ID not found.")
            else:
                print("Product deleted successfully.")
    except Exception as e:
        conn.rollback()
        print(f"Error deleting product: {e}")

def main():
    conn = connect_db()
    create_table(conn)

    while True:
        print("\n--- Product Management ---")
        print("1. Create Product")
        print("2. Read Products")
        print("3. Update Product")
        print("4. Delete Product")
        print("5. Exit")

        choice = input("Enter choice (1-5): ")

        if choice == '1':
            create_product(conn)
        elif choice == '2':
            read_products(conn)
        elif choice == '3':
            update_product(conn)
        elif choice == '4':
            delete_product(conn)
        elif choice == '5':
            conn.close()
            print("Exiting program.")
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()
