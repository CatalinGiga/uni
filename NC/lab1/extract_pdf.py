import sys
try:
    from PyPDF2 import PdfReader
    reader = PdfReader('/home/cata/Code/uni/NC/lab1/L1_NC.pdf')
    for i, page in enumerate(reader.pages):
        text = page.extract_text()
        print(f'--- PAGE {i+1} ---')
        print(text)
except ImportError:
    try:
        import subprocess
        result = subprocess.run(['pdftotext', '/home/cata/Code/uni/NC/lab1/L1_NC.pdf', '-'], capture_output=True, text=True)
        if result.returncode == 0:
            print(result.stdout)
        else:
            print("pdftotext failed:", result.stderr, file=sys.stderr)
            # Try pymupdf
            try:
                import fitz
                doc = fitz.open('/home/cata/Code/uni/NC/lab1/L1_NC.pdf')
                for i, page in enumerate(doc):
                    text = page.get_text()
                    print(f'--- PAGE {i+1} ---')
                    print(text)
            except ImportError:
                print("No PDF library available. Install PyPDF2: pip install PyPDF2", file=sys.stderr)
    except Exception as e:
        print(f"Error: {e}", file=sys.stderr)
