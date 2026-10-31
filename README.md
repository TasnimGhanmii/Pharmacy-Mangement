# 💊 Pharmacy Management System

A Java desktop application for running the day-to-day operations of a pharmacy: managing the medicine catalogue, handling sales, and generating printable PDF bills. Access is split between two roles, **Admin** and **Pharmacist**, so that sensitive operations stay restricted while daily sales stay fast.

> **Status:** Academic / personal project · Actively improvable (see [Roadmap](#roadmap))

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Roles & Permissions](#roles--permissions)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Bill Generation](#bill-generation)
- [Data Model](#data-model)
- [Screenshots](#screenshots)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [Author](#author)

---

## Overview

Small and mid-sized pharmacies often track stock and sales on paper or in spreadsheets, which leads to stock-outs, pricing mistakes, and slow checkout. This project centralizes those workflows in a single application:

- A **catalogue of medicines** with full CRUD (create, read, update, delete)
- **Role-based access** for administrators and pharmacists
- **Automatic bill generation** exported as PDF for every sale

## Features

### Medicine Management
- Add a new medicine (name, category, price, quantity in stock, expiry date, …)
- View and search the full catalogue
- Update medicine details and stock levels
- Delete obsolete or expired medicines

### Sales & Billing
- Build a sale by selecting medicines and quantities
- Automatic total calculation
- Stock decremented after each validated sale
- **PDF bill generated per sale** (sample outputs: `Bill_1.pdf`, `Bill_3.pdf`)

### User & Access Management
- Secure login with role detection
- Admin-only management of pharmacist accounts
- Pharmacist-only view focused on sales and catalogue lookup

## Roles & Permissions

| Capability                         | Admin | Pharmacist |
|------------------------------------|:-----:|:----------:|
| Log in                             |  ✅   |     ✅     |
| View medicines                     |  ✅   |     ✅     |
| Add / edit / delete medicines      |  ✅   |  *(configure)* |
| Create a sale & generate a bill    |  ✅   |     ✅     |
| Manage pharmacist accounts         |  ✅   |     ❌     |

> Adjust this table to match the exact permissions implemented in your code.

## Tech Stack

| Layer            | Technology                                              |
|------------------|---------------------------------------------------------|
| Language         | Java                                                    |
| IDE / Build      | IntelliJ IDEA (`.iml` project, dependencies in `LIBS/`) |
| UI               | Java desktop UI (Swing / JavaFX – *specify*)            |
| Persistence      | *(specify: MySQL / SQLite / files)* via JDBC            |
| PDF generation   | *(specify: iText / OpenPDF / PDFBox)* – JAR in `LIBS/`  |

External libraries are bundled as JAR files in the `LIBS/` folder so the project runs without a package manager.

## Project Structure

```
Pharmacy-Mangement/
├── .idea/                   # IntelliJ IDEA project settings
├── LIBS/                    # Third-party JARs (JDBC driver, PDF library, ...)
├── src/                     # Application source code
├── Bill_1.pdf               # Sample generated bill
├── Bill_3.pdf               # Sample generated bill
├── Pharmacy Mangement.iml   # IntelliJ module file
└── .gitignore
```

> Expand the `src/` section with your actual packages (for example `model`, `dao`, `ui`, `service`, `util`) so new contributors can navigate quickly.

## Getting Started

### Prerequisites

- **JDK 11+** (adjust to the version you developed with)
- **IntelliJ IDEA** (Community Edition is enough)
- A running database instance, if the app uses one *(specify)*

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/TasnimGhanmii/Pharmacy-Mangement.git
   cd Pharmacy-Mangement
   ```

2. **Open the project in IntelliJ IDEA**
   `File → Open…` and select the project folder.

3. **Register the libraries**
   `File → Project Structure → Libraries → + → Java` and add every `.jar` inside `LIBS/`.

4. **Configure the database** *(if applicable)*
   - Create the database and tables using the schema in [Data Model](#data-model)
   - Update the connection settings (URL, user, password) in the data-access class

5. **Run the application**
   Locate the class containing the `main` method under `src/` and run it.

### Default Credentials *(optional)*

| Role       | Username | Password |
|------------|----------|----------|
| Admin      | `admin`  | `admin`  |

> ⚠️ Change default credentials before any real-world use, and never commit real passwords.

## Usage

1. **Log in** with your Admin or Pharmacist account.
2. **Admin:** manage the medicine catalogue and pharmacist accounts from the dashboard.
3. **Pharmacist:** search medicines, add them to a sale with quantities, and validate.
4. **Validate the sale** to update stock and generate the PDF bill.

## Bill Generation

Each validated sale produces a PDF bill named `Bill_<id>.pdf` containing:

- Bill number and date
- Purchased medicines with unit price and quantity
- Line totals and the grand total

See `Bill_1.pdf` and `Bill_3.pdf` in the repository root for real examples.

## Data Model

A suggested relational schema (adapt to what the code actually uses):

```
User(id, username, password_hash, role)                 -- role: ADMIN | PHARMACIST
Medicine(id, name, category, price, quantity, expiry_date)
Bill(id, date, total, pharmacist_id → User.id)
BillItem(id, bill_id → Bill.id, medicine_id → Medicine.id, quantity, unit_price)
```

## Screenshots

> Add screenshots here to make the project instantly understandable.

| Login | Dashboard | Medicine CRUD | Bill |
|-------|-----------|---------------|------|
| `docs/login.png` | `docs/dashboard.png` | `docs/medicines.png` | `docs/bill.png` |

## Roadmap

- [ ] Hash passwords (BCrypt) instead of storing them in plain text
- [ ] Low-stock and near-expiry alerts
- [ ] Sales history and revenue dashboard
- [ ] Search and filter by category, supplier, or expiry
- [ ] Move to Maven/Gradle to replace the `LIBS/` folder
- [ ] Add unit tests for the service layer
- [ ] Remove `.idea/` and `.iml` files from version control

## Contributing

Contributions, issues, and feature requests are welcome.

1. Fork the project
2. Create a feature branch: `git checkout -b feature/my-feature`
3. Commit your changes: `git commit -m "Add my feature"`
4. Push the branch: `git push origin feature/my-feature`
5. Open a Pull Request

## Author

**Tasnim Ghanmi**
GitHub: [@TasnimGhanmii](https://github.com/TasnimGhanmii)
