import os
import subprocess

def execute_tarql(tarql_file, csv_file, output_file):
    try:
        command = ["tarql", tarql_file, csv_file]
        with open(output_file, "w") as f:
            subprocess.run(command, check=True, stdout=f, stderr=subprocess.PIPE)
        print(f"Conversion réussie : {csv_file} → {output_file}")
    except subprocess.CalledProcessError as e:
        print(f"Erreur lors de l'exécution de TARQL : {e.stderr}")
    except FileNotFoundError:
        print(f"Erreur : TARQL ou fichier manquant pour {tarql_file} et {csv_file}.")

if __name__ == "__main__":
    csv_dir = "data"
    tarql_dir = "tarql"
    output_dir = "ttl"

    os.makedirs(output_dir, exist_ok=True)

    mappings = {
        "clubs.csv": "clubs.tarql",
        "players.csv": "players.tarql",
        "transfers.csv": "transfers.tarql",
        "competitions.csv": ["competitions.tarql", "country.tarql"],  # Plusieurs TARQL pour un CSV
        "games.csv": "games.tarql"
    }

    for csv_file, tarql_files in mappings.items():
        csv_path = os.path.join(csv_dir, csv_file)

        if not os.path.exists(csv_path):
            print(f"Erreur : Le fichier CSV {csv_path} est introuvable.")
            continue

        if isinstance(tarql_files, list):
            for tarql_file in tarql_files:
                tarql_path = os.path.join(tarql_dir, tarql_file)
                output_file = os.path.join(output_dir, f"{os.path.splitext(tarql_file)[0]}.ttl")

                if not os.path.exists(tarql_path):
                    print(f"Erreur : Le fichier TARQL {tarql_path} est introuvable.")
                    continue

                execute_tarql(tarql_path, csv_path, output_file)
        else:
            tarql_path = os.path.join(tarql_dir, tarql_files)
            output_file = os.path.join(output_dir, f"{os.path.splitext(tarql_files)[0]}.ttl")

            if not os.path.exists(tarql_path):
                print(f"Erreur : Le fichier TARQL {tarql_path} est introuvable.")
                continue

            execute_tarql(tarql_path, csv_path, output_file)